package crit.magec.client;

import crit.magec.components.ModComponents;
import crit.magec.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class MagecClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, list) -> {
			if (!itemStack.isOf(ModItems.CONTRACT)) {
				return;
			}

			String tooltip = itemStack.get(ModComponents.BOUND_TO_NAME);
			list.add(Text.translatable("item.magec.contact.tooltip", tooltip).formatted(Formatting.GOLD));
		});
	}
}