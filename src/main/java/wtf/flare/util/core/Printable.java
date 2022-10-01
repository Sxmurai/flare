package wtf.flare.util.core;

import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public interface Printable extends Wrapper {

    String PREFIX = Formatting.RED + "[Flare]" + Formatting.RESET;

    default void print(String message) {
        mc.inGameHud.getChatHud().addMessage(Text.literal(PREFIX)
                .setStyle(Style.EMPTY.withColor(Formatting.GRAY))
                .append(" ")
                .append(message));
    }
}
