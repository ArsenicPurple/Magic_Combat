package net.I_love_arsenic.magcom.client.guis;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class GuideGui extends Screen {
    public GuideGui(String title) {
        super(new StringTextComponent(title));
    }

    @Override
    protected void insertText(String text, boolean overwrite) {
        text = "peepee poopoo";
        super.insertText(text, overwrite);
    }

    @Override
    public void init(Minecraft minecraft, int width, int height) {
        super.init(minecraft, width, height);
    }


}
