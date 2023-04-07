package util;

public class LoopUtil {
    private  static boolean loop = true;

    public static boolean isLoop() {
        return loop;
    }

    public static void setLoop(boolean loop) {
        LoopUtil.loop = loop;
    }

}
