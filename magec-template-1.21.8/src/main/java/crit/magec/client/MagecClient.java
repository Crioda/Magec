package crit.magec.client;

import crit.magec.components.ModComponents;
import crit.magec.entity.ModEntities;
import crit.magec.entity.client.DivinityTestModel;
import crit.magec.entity.client.DivinityTestRenderer;
import crit.magec.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.text.Style;
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
			list.add(Text.translatable("item.magec.contact.tooltip", tooltip).formatted(Formatting.GOLD).withColor(12));
		});

		EntityModelLayerRegistry.registerModelLayer(DivinityTestModel.DIVINITYTEST, DivinityTestModel::getTexturedModelData);
		EntityRendererRegistry.register(ModEntities.DIVINITYTEST, DivinityTestRenderer::new);


	}
}