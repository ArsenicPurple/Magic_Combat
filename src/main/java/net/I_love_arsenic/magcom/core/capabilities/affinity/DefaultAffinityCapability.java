package net.I_love_arsenic.magcom.core.capabilities.affinity;

import java.util.Random;

public class DefaultAffinityCapability implements IAffinityCapability {

    private int type = new Random().nextInt(9) - 1;

    @Override
    public void setAffinity(int type) { this.type = type; }

    @Override
    public int getAffinity() { return type; }
}
