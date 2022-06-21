package games.moegirl.sinocraft.sinocore.api.utility.texture;

import java.awt.*;

public record PointEntry(String name, int x, int y) {

    public int u() {
        return x;
    }

    public int v() {
        return y;
    }

    public int w() {
        return x;
    }

    public int h() {
        return y;
    }

    public Point toPoint() {
        return new Point(x, y);
    }
}
