package tk.booky.antiadminitems.utils;
// Created by booky10 in AntiAdminItems (10:58 25.07.21)

import org.bukkit.Material;

public class AdminItems {

    public static boolean preventUsage(Material material, boolean creative) {
        return creative ? preventCreativeUsage(material) : preventSurvivalUsage(material);
    }

    public static boolean preventCreativeUsage(Material material) {
        // noinspection EnhancedSwitchMigration
        switch (material) {
            // <editor-fold defaultstate="collapsed" desc="preventCreativeUsage">
            case WANDERING_TRADER_SPAWN_EGG:
            case ZOMBIFIED_PIGLIN_SPAWN_EGG:
            case ZOMBIE_VILLAGER_SPAWN_EGG:
            case WITHER_SKELETON_SPAWN_EGG:
            case ELDER_GUARDIAN_SPAWN_EGG:
            case SKELETON_HORSE_SPAWN_EGG:
            case REPEATING_COMMAND_BLOCK:
            case TROPICAL_FISH_SPAWN_EGG:
            case PIGLIN_BRUTE_SPAWN_EGG:
            case TRADER_LLAMA_SPAWN_EGG:
            case ZOMBIE_HORSE_SPAWN_EGG:
            case LIGHT_BLUE_SHULKER_BOX:
            case LIGHT_GRAY_SHULKER_BOX:
            case COMMAND_BLOCK_MINECART:
            case CAVE_SPIDER_SPAWN_EGG:
            case VINDICATOR_SPAWN_EGG:
            case POLAR_BEAR_SPAWN_EGG:
            case PUFFERFISH_SPAWN_EGG:
            case SILVERFISH_SPAWN_EGG:
            case MAGMA_CUBE_SPAWN_EGG:
            case CHAIN_COMMAND_BLOCK:
            case MAGENTA_SHULKER_BOX:
            case MOOSHROOM_SPAWN_EGG:
            case ENDERMITE_SPAWN_EGG:
            case GUARDIAN_SPAWN_EGG:
            case VILLAGER_SPAWN_EGG:
            case ORANGE_SHULKER_BOX:
            case YELLOW_SHULKER_BOX:
            case PURPLE_SHULKER_BOX:
            case ENDERMAN_SPAWN_EGG:
            case PILLAGER_SPAWN_EGG:
            case SKELETON_SPAWN_EGG:
            case SHULKER_SPAWN_EGG:
            case BLACK_SHULKER_BOX:
            case WHITE_SHULKER_BOX:
            case CREEPER_SPAWN_EGG:
            case DOLPHIN_SPAWN_EGG:
            case BROWN_SHULKER_BOX:
            case DROWNED_SPAWN_EGG:
            case STRIDER_SPAWN_EGG:
            case GREEN_SHULKER_BOX:
            case RAVAGER_SPAWN_EGG:
            case PHANTOM_SPAWN_EGG:
            case CHICKEN_SPAWN_EGG:
            case LIME_SHULKER_BOX:
            case PIGLIN_SPAWN_EGG:
            case RABBIT_SPAWN_EGG:
            case SALMON_SPAWN_EGG:
            case TURTLE_SPAWN_EGG:
            case DONKEY_SPAWN_EGG:
            case PINK_SHULKER_BOX:
            case BLUE_SHULKER_BOX:
            case EVOKER_SPAWN_EGG:
            case GRAY_SHULKER_BOX:
            case PARROT_SPAWN_EGG:
            case CYAN_SHULKER_BOX:
            case END_PORTAL_FRAME:
            case ZOGLIN_SPAWN_EGG:
            case ZOMBIE_SPAWN_EGG:
            case SPIDER_SPAWN_EGG:
            case OCELOT_SPAWN_EGG:
            case LINGERING_POTION:
            case HOGLIN_SPAWN_EGG:
            case HORSE_SPAWN_EGG:
            case BLAZE_SPAWN_EGG:
            case PANDA_SPAWN_EGG:
            case SQUID_SPAWN_EGG:
            case STRAY_SPAWN_EGG:
            case WITCH_SPAWN_EGG:
            case GHAST_SPAWN_EGG:
            case LLAMA_SPAWN_EGG:
            case RED_SHULKER_BOX:
            case STRUCTURE_BLOCK:
            case SHEEP_SPAWN_EGG:
            case SLIME_SPAWN_EGG:
            case HUSK_SPAWN_EGG:
            case MULE_SPAWN_EGG:
            case STRUCTURE_VOID:
            case WOLF_SPAWN_EGG:
            case BAT_SPAWN_EGG:
            case PIG_SPAWN_EGG:
            case BEE_SPAWN_EGG:
            case COW_SPAWN_EGG:
            case COMMAND_BLOCK:
            case SPLASH_POTION:
            case CAT_SPAWN_EGG:
            case COD_SPAWN_EGG:
            case FOX_SPAWN_EGG:
            case VEX_SPAWN_EGG:
            case SHULKER_BOX:
            case DEBUG_STICK:
            case SPAWNER:
            case BARRIER:
            case BEDROCK:
            case JIGSAW:
            case POTION:
                // </editor-fold>
                return true;
            default:
                return false;
        }
    }

    public static boolean preventSurvivalUsage(Material material) {
        // noinspection EnhancedSwitchMigration
        switch (material) {
            // <editor-fold defaultstate="collapsed" desc="preventSurvivalUsage">
            case WANDERING_TRADER_SPAWN_EGG:
            case ZOMBIFIED_PIGLIN_SPAWN_EGG:
            case ZOMBIE_VILLAGER_SPAWN_EGG:
            case WITHER_SKELETON_SPAWN_EGG:
            case ELDER_GUARDIAN_SPAWN_EGG:
            case SKELETON_HORSE_SPAWN_EGG:
            case REPEATING_COMMAND_BLOCK:
            case TROPICAL_FISH_SPAWN_EGG:
            case PIGLIN_BRUTE_SPAWN_EGG:
            case TRADER_LLAMA_SPAWN_EGG:
            case ZOMBIE_HORSE_SPAWN_EGG:
            case COMMAND_BLOCK_MINECART:
            case CAVE_SPIDER_SPAWN_EGG:
            case VINDICATOR_SPAWN_EGG:
            case POLAR_BEAR_SPAWN_EGG:
            case PUFFERFISH_SPAWN_EGG:
            case SILVERFISH_SPAWN_EGG:
            case MAGMA_CUBE_SPAWN_EGG:
            case CHAIN_COMMAND_BLOCK:
            case MOOSHROOM_SPAWN_EGG:
            case ENDERMITE_SPAWN_EGG:
            case GUARDIAN_SPAWN_EGG:
            case VILLAGER_SPAWN_EGG:
            case ENDERMAN_SPAWN_EGG:
            case PILLAGER_SPAWN_EGG:
            case SKELETON_SPAWN_EGG:
            case CREEPER_SPAWN_EGG:
            case DOLPHIN_SPAWN_EGG:
            case DROWNED_SPAWN_EGG:
            case STRIDER_SPAWN_EGG:
            case RAVAGER_SPAWN_EGG:
            case PHANTOM_SPAWN_EGG:
            case CHICKEN_SPAWN_EGG:
            case PIGLIN_SPAWN_EGG:
            case RABBIT_SPAWN_EGG:
            case SALMON_SPAWN_EGG:
            case TURTLE_SPAWN_EGG:
            case DONKEY_SPAWN_EGG:
            case EVOKER_SPAWN_EGG:
            case PARROT_SPAWN_EGG:
            case END_PORTAL_FRAME:
            case ZOGLIN_SPAWN_EGG:
            case ZOMBIE_SPAWN_EGG:
            case SPIDER_SPAWN_EGG:
            case OCELOT_SPAWN_EGG:
            case HOGLIN_SPAWN_EGG:
            case HORSE_SPAWN_EGG:
            case BLAZE_SPAWN_EGG:
            case PANDA_SPAWN_EGG:
            case SQUID_SPAWN_EGG:
            case STRAY_SPAWN_EGG:
            case WITCH_SPAWN_EGG:
            case GHAST_SPAWN_EGG:
            case LLAMA_SPAWN_EGG:
            case STRUCTURE_BLOCK:
            case SHEEP_SPAWN_EGG:
            case SLIME_SPAWN_EGG:
            case HUSK_SPAWN_EGG:
            case MULE_SPAWN_EGG:
            case STRUCTURE_VOID:
            case WOLF_SPAWN_EGG:
            case BAT_SPAWN_EGG:
            case PIG_SPAWN_EGG:
            case BEE_SPAWN_EGG:
            case COW_SPAWN_EGG:
            case COMMAND_BLOCK:
            case CAT_SPAWN_EGG:
            case COD_SPAWN_EGG:
            case FOX_SPAWN_EGG:
            case VEX_SPAWN_EGG:
            case DEBUG_STICK:
            case SPAWNER:
            case BARRIER:
            case BEDROCK:
            case JIGSAW:
                // </editor-fold>
                return true;
            default:
                return false;
        }
    }
}
