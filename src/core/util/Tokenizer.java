package core.util;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.isLetterOrDigit;

public class Tokenizer {

    /**
     * 现在已经读到的位置的指针
     */
    private int currentIndex = 0;

    /**
     * 正在被处理的文段
     */
    private String text;

    /**
     * @param text 需要处理的文段
     */
    public Tokenizer(String text) {
        this.text = text;
    }

    /**
     * @return 指针是否以达到最大值
     */
    public boolean hasNext() {
        return currentIndex < text.length();
    }

    /**
     * 将指针拨回到0
     */
    public void reset() {
        currentIndex = 0;
    }

    /**
     * 根据指针返回下一个字符并将其向后拨一位
     *
     * @return 下一位字符
     */
    public char nextChar() {
        return text.charAt(currentIndex++);
    }

    /**
     * 移动指针至下一个非空格字符
     */
    public void skipWhitespaces() {
        while (hasNext())
            if (!Character.isWhitespace(nextChar())) {
                goBack();
                return;
            }
    }

    /**
     * 在文段中找到下一个词，自动排除前面的空格，碰到非字母数字的时候断句
     *
     * @return 找到的词
     */
    public String nextName() {
        skipWhitespaces();
        int startPosition = currentIndex;

        while (hasNext())
            if (!isLetterOrDigit((nextChar()))) {
                goBack();
                break;
            }
        int endPosition = currentIndex;

        return text.substring(startPosition, endPosition);
    }

    /**
     * 当确定程序后面必须要出现一系列固定表达的时候，调用这个方法，会自动跳过之前空格
     * （除非要跳过的就是空格），检测这些表达是否被写出，并移动指针跳过已知的这段表达
     *
     * @param segment 要跳过的部分
     * @return 是否成功的找到了要跳过的部分并跳过
     */
    public boolean skip(String segment) {
        if (segment.charAt(0) != ' ')
            skipWhitespaces();

        int movePlace = segment.length();
        if (text.substring(currentIndex, currentIndex + movePlace).equals(segment)) {
            currentIndex += movePlace;
            return true;
        }
        return false;
    }

    public String readTill(char... chars) {
        int startPosition = currentIndex;

        outer:
        while (hasNext()) {
            char nextChar = nextChar();
            for (char c: chars)
                if (nextChar == c) {
                    goBack();
                    break outer;
                }
        }
        int endPosition = currentIndex;

        return text.substring(startPosition, endPosition);
    }

    /**
     * 将指针向前拨一位
     */
    public void goBack() {
        goBack(1);
    }

    /**
     * 将指针向前拨数位
     *
     * @param places 位数
     */
    public void goBack(int places) {
        currentIndex -= places;
    }

    /**
     * 让外界可以查看指针位置
     *
     * @return 指针位置
     */
    public int getCurrentIndex() {
        return currentIndex;
    }
}
