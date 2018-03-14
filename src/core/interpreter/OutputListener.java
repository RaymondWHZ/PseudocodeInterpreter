package core.interpreter;

public interface OutputListener {

    /**
     * 实现此方法以处理输出事件
     *
     * @param text 输出文本
     */
    public void outputOccur(String text);
}
