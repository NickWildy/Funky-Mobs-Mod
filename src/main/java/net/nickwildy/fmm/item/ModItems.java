package net.nickwildy.fmm.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.nickwildy.fmm.FunkyMobsMod;
import net.nickwildy.fmm.entity.ModEntities;

import java.util.function.Function;

public class ModItems {
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        // Create the item key.
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(FunkyMobsMod.MOD_ID, name));

        // Create the item instance.
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        // Register the item.
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    public static final Item BALDI_SPAWN_EGG = register("baldi_spawn_egg",
            settings -> new SpawnEggItem(ModEntities.BALDI, settings), new Item.Settings());

    public static final Item RULER = register(
            "ruler",
            Item::new,
            new Item.Settings().sword(ToolMaterial.IRON, 4.0f, 2.0f)
    );

    public static void registerModItems() {
        FunkyMobsMod.LOGGER.info("Registering Mod Items of " + FunkyMobsMod.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                .register((itemGroup) -> itemGroup.addAfter(Items.MACE ,ModItems.RULER));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS)
                .register((itemGroup) -> itemGroup.add(ModItems.BALDI_SPAWN_EGG));
    }
}
