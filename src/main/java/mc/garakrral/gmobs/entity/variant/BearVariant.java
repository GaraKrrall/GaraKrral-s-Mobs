package mc.garakrral.gmobs.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum BearVariant {
    NORMAL(0),
    HONEY(1);

    private final int id;

    private static final BearVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(BearVariant::getId)).toArray(BearVariant[]::new);

    BearVariant(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static BearVariant byId(int id){
        return BY_ID[id % BY_ID.length];
    }
}
