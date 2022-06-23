package com.minecraftabnormals.endergetic.core.registry;

import java.util.concurrent.Callable;

import com.minecraftabnormals.abnormals_core.common.blocks.*;
import com.minecraftabnormals.endergetic.core.registry.util.EndergeticBlockSubRegistryHelper;
import com.mojang.datafixers.util.Pair;
import com.minecraftabnormals.abnormals_core.common.blocks.chest.AbnormalsChestBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.chest.AbnormalsTrappedChestBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.sign.*;
import com.minecraftabnormals.abnormals_core.common.blocks.wood.*;
import com.minecraftabnormals.endergetic.client.renderers.item.EETileEntityItemRenderer;
import com.minecraftabnormals.endergetic.common.EEProperties;
import com.minecraftabnormals.endergetic.common.blocks.*;
import com.minecraftabnormals.endergetic.common.blocks.poise.*;
import com.minecraftabnormals.endergetic.common.blocks.poise.boof.*;
import com.minecraftabnormals.endergetic.common.blocks.poise.hive.*;
import com.minecraftabnormals.endergetic.common.tileentities.BolloomBudTileEntity;
import com.minecraftabnormals.endergetic.common.tileentities.PuffBugHiveTileEntity;
import com.minecraftabnormals.endergetic.core.EndergeticExpansion;

import net.minecraft.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.Lantern;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

@SuppressWarnings("deprecation")
@Mod.EventBusSubscriber(modid = EndergeticExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class EEBlocks {
	private static final EndergeticBlockSubRegistryHelper HELPER = EndergeticExpansion.REGISTRY_HELPER.getBlockSubHelper();

	public static final RegistryObject<Block> CORROCK_OVERWORLD                 = HELPER.createBlock("overworld_corrock", () -> new CorrockPlantBlock(EEProperties.getCorrockBase(MaterialColor.TERRACOTTA_BROWN, false), false), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> PETRIFIED_CORROCK_OVERWORLD       = HELPER.createBlock("petrified_overworld_corrock", () -> new CorrockPlantBlock(EEProperties.getCorrockBase(MaterialColor.TERRACOTTA_BROWN, false), true), null);
	public static final RegistryObject<Block> CORROCK_NETHER                    = HELPER.createBlock("nether_corrock", () -> new CorrockPlantBlock(EEProperties.getCorrockBase(MaterialColor.TERRACOTTA_RED, false), false), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> PETRIFIED_CORROCK_NETHER          = HELPER.createBlock("petrified_nether_corrock", () -> new CorrockPlantBlock(EEProperties.getCorrockBase(MaterialColor.TERRACOTTA_RED, false), true), null);
	public static final RegistryObject<Block> CORROCK_END                       = HELPER.createBlock("end_corrock", () -> new CorrockPlantBlock(EEProperties.getCorrockBase(MaterialColor.TERRACOTTA_PURPLE, false), false), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> PETRIFIED_CORROCK_END             = HELPER.createBlock("petrified_end_corrock", () -> new CorrockPlantBlock(EEProperties.getCorrockBase(MaterialColor.TERRACOTTA_PURPLE, false), true), null);
	public static final RegistryObject<Block> SPECKLED_OVERWORLD_CORROCK        = HELPER.createBlock("speckled_overworld_corrock", () -> new SpeckledCorrockBlock(Properties.copy(Blocks.END_STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> PETRIFIED_SPECKLED_OVERWORLD_CORROCK = HELPER.createBlock("petrified_speckled_overworld_corrock", () -> new Block(Properties.copy(Blocks.END_STONE)), null);
	public static final RegistryObject<Block> SPECKLED_NETHER_CORROCK           = HELPER.createBlock("speckled_nether_corrock", () -> new SpeckledCorrockBlock(Properties.copy(Blocks.END_STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> PETRIFIED_SPECKLED_NETHER_CORROCK = HELPER.createBlock("petrified_speckled_nether_corrock", () -> new Block(Properties.copy(Blocks.END_STONE)), null);
	public static final RegistryObject<Block> SPECKLED_END_CORROCK              = HELPER.createBlock("speckled_end_corrock", () -> new SpeckledCorrockBlock(Properties.copy(Blocks.END_STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> PETRIFIED_SPECKLED_END_CORROCK    = HELPER.createBlock("petrified_speckled_end_corrock", () -> new Block(Properties.copy(Blocks.END_STONE)), null);
	public static final RegistryObject<Block> CORROCK_OVERWORLD_BLOCK           = HELPER.createBlock("overworld_corrock_block", () -> new CorrockBlock(EEProperties.getCorrockBase(MaterialColor.TERRACOTTA_BROWN, true), SPECKLED_OVERWORLD_CORROCK, CORROCK_OVERWORLD, false), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> PETRIFIED_CORROCK_OVERWORLD_BLOCK = HELPER.createBlock("petrified_overworld_corrock_block", () -> new Block(EEProperties.getCorrockBase(MaterialColor.TERRACOTTA_BROWN, true)), null);
	public static final RegistryObject<Block> CORROCK_NETHER_BLOCK              = HELPER.createBlock("nether_corrock_block", () -> new CorrockBlock(EEProperties.getCorrockBase(MaterialColor.TERRACOTTA_RED, true), SPECKLED_NETHER_CORROCK, CORROCK_NETHER, false), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> PETRIFIED_CORROCK_NETHER_BLOCK    = HELPER.createBlock("petrified_nether_corrock_block", () -> new Block(EEProperties.getCorrockBase(MaterialColor.TERRACOTTA_RED, true)), null);
	public static final RegistryObject<Block> CORROCK_END_BLOCK                 = HELPER.createBlock("end_corrock_block", () -> new CorrockBlock(EEProperties.getCorrockBase(MaterialColor.TERRACOTTA_PURPLE, true), SPECKLED_END_CORROCK, CORROCK_END, false), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> PETRIFIED_CORROCK_END_BLOCK       = HELPER.createBlock("petrified_end_corrock_block", () -> new Block(EEProperties.getCorrockBase(MaterialColor.TERRACOTTA_PURPLE, true)), null);
	public static final RegistryObject<CorrockCrownWallBlock> CORROCK_CROWN_OVERWORLD_WALL           = HELPER.createBlockNoItem("overworld_wall_corrock_crown", () -> new CorrockCrownWallBlock(EEProperties.getGlowingCorrockBase(MaterialColor.COLOR_BROWN),CorrockCrownBlock.DimensionalType.OVERWORLD,  false));
	public static final RegistryObject<CorrockCrownWallBlock> PETRIFIED_CORROCK_CROWN_OVERWORLD_WALL = HELPER.createBlockNoItem("petrified_overworld_wall_corrock_crown", () -> new CorrockCrownWallBlock(EEProperties.getGlowingCorrockBase(MaterialColor.COLOR_BROWN), CorrockCrownBlock.DimensionalType.OVERWORLD, true));
	public static final RegistryObject<CorrockCrownBlock> CORROCK_CROWN_OVERWORLD_STANDING           = HELPER.createCorrockStandingBlock("overworld_corrock_crown", () -> new CorrockCrownStandingBlock(EEProperties.getGlowingCorrockBase(MaterialColor.COLOR_BROWN), CorrockCrownBlock.DimensionalType.OVERWORLD, false), CORROCK_CROWN_OVERWORLD_WALL, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CorrockCrownBlock> PETRIFIED_CORROCK_CROWN_OVERWORLD_STANDING = HELPER.createCorrockStandingBlock("petrified_overworld_corrock_crown", () -> new CorrockCrownStandingBlock(EEProperties.getGlowingCorrockBase(MaterialColor.COLOR_BROWN), CorrockCrownBlock.DimensionalType.OVERWORLD, true), PETRIFIED_CORROCK_CROWN_OVERWORLD_WALL, null);
	public static final RegistryObject<CorrockCrownWallBlock> CORROCK_CROWN_NETHER_WALL              = HELPER.createBlockNoItem("nether_wall_corrock_crown", () -> new CorrockCrownWallBlock(EEProperties.getGlowingCorrockBase(MaterialColor.COLOR_RED), CorrockCrownBlock.DimensionalType.NETHER, false));
	public static final RegistryObject<CorrockCrownWallBlock> PETRIFIED_CORROCK_CROWN_NETHER_WALL    = HELPER.createBlockNoItem("petrified_nether_wall_corrock_crown", () -> new CorrockCrownWallBlock(EEProperties.getGlowingCorrockBase(MaterialColor.COLOR_RED), CorrockCrownBlock.DimensionalType.NETHER, true));
	public static final RegistryObject<CorrockCrownBlock> CORROCK_CROWN_NETHER_STANDING              = HELPER.createCorrockStandingBlock("nether_corrock_crown", () -> new CorrockCrownStandingBlock(EEProperties.getGlowingCorrockBase(MaterialColor.COLOR_RED), CorrockCrownBlock.DimensionalType.NETHER, false), CORROCK_CROWN_NETHER_WALL, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CorrockCrownBlock> PETRIFIED_CORROCK_CROWN_NETHER_STANDING    = HELPER.createCorrockStandingBlock("petrified_nether_corrock_crown", () -> new CorrockCrownStandingBlock(EEProperties.getGlowingCorrockBase(MaterialColor.COLOR_RED), CorrockCrownBlock.DimensionalType.NETHER, true), PETRIFIED_CORROCK_CROWN_NETHER_WALL, null);
	public static final RegistryObject<CorrockCrownWallBlock> CORROCK_CROWN_END_WALL                 = HELPER.createBlockNoItem("end_wall_corrock_crown", () -> new CorrockCrownWallBlock(EEProperties.getGlowingCorrockBase(MaterialColor.COLOR_PURPLE), CorrockCrownBlock.DimensionalType.END, false));
	public static final RegistryObject<CorrockCrownWallBlock> PETRIFIED_CORROCK_CROWN_END_WALL       = HELPER.createBlockNoItem("petrified_end_wall_corrock_crown", () -> new CorrockCrownWallBlock(EEProperties.getGlowingCorrockBase(MaterialColor.COLOR_PURPLE), CorrockCrownBlock.DimensionalType.END, true));
	public static final RegistryObject<CorrockCrownBlock> CORROCK_CROWN_END_STANDING                 = HELPER.createCorrockStandingBlock("end_corrock_crown", () -> new CorrockCrownStandingBlock(EEProperties.getGlowingCorrockBase(MaterialColor.COLOR_PURPLE), CorrockCrownBlock.DimensionalType.END, false), CORROCK_CROWN_END_WALL, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CorrockCrownBlock> PETRIFIED_CORROCK_CROWN_END_STANDING       = HELPER.createCorrockStandingBlock("petrified_end_corrock_crown", () -> new CorrockCrownStandingBlock(EEProperties.getGlowingCorrockBase(MaterialColor.COLOR_PURPLE), CorrockCrownBlock.DimensionalType.END, true), PETRIFIED_CORROCK_CROWN_END_WALL, null);
	public static final RegistryObject<Block> INFESTED_CORROCK                                       = HELPER.createBlock("infested_corrock", () -> new InfestedCorrockBlock(EEProperties.INFESTED_CORROCK), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> PETRIFIED_INFESTED_CORROCK                             = HELPER.createBlock("petrified_infested_corrock", () -> new Block(EEProperties.PETRIFIED_INFESTED_CORROCK), null);

	/*
	 * Poise Forest
	 */
	public static final RegistryObject<Block> EUMUS_POISMOSS 		= HELPER.createBlock("eumus_poismoss", () -> new PoismossEumusBlock(EEProperties.EUMUS_POISMOSS), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> POISMOSS              = HELPER.createBlock("poismoss", () -> new PoismossBlock(EEProperties.getPoiseGrass(false)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> POISE_BUSH            = HELPER.createBlock("poise_bush", () -> new PoiseBushBlock(EEProperties.getPoiseGrass(true)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> TALL_POISE_BUSH       = HELPER.createBlock("tall_poise_bush", () -> new PoiseTallBushBlock(EEProperties.getPoiseGrass(true)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> POISE_CLUSTER         = HELPER.createBlock("poise_cluster", () -> new PoiseClusterBlock(EEProperties.POISE_CLUSTER.randomTicks()), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> STRIPPED_POISE_STEM    = HELPER.createBlock("stripped_poise_stem", () -> new StrippedLogBlock(EEProperties.POISE_WOOD), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_POISE_WOOD   = HELPER.createBlock("stripped_poise_wood", () -> new StrippedWoodBlock(EEProperties.POISE_WOOD), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> POISE_STEM            = HELPER.createBlock("poise_stem", () -> new AbnormalsLogBlock(EEBlocks.STRIPPED_POISE_STEM, EEProperties.POISE_WOOD), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> POISE_WOOD            = HELPER.createBlock("poise_wood", () -> new WoodBlock(EEBlocks.STRIPPED_POISE_WOOD, EEProperties.POISE_WOOD), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> GLOWING_POISE_STEM   	= HELPER.createBlock("glowing_poise_stem", () -> new GlowingPoiseStemBlock(STRIPPED_POISE_STEM, EEProperties.POISE_LOG_GLOWING), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> GLOWING_POISE_WOOD   	= HELPER.createBlock("glowing_poise_wood", () -> new GlowingPoiseWoodBlock(STRIPPED_POISE_WOOD, EEProperties.POISE_LOG_GLOWING), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> POISE_PLANKS          = HELPER.createBlock("poise_planks", () -> new PlanksBlock(EEProperties.POISE_WOOD), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> POISE_DOOR            = HELPER.createTallBlock("poise_door", () -> new WoodDoorBlock(EEProperties.POISE_WOOD_NOT_SOLID), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> POISE_SLAB            = HELPER.createBlock("poise_slab", () -> new WoodSlabBlock(EEProperties.POISE_WOOD), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> POISE_STAIRS          = HELPER.createBlock("poise_stairs", () -> new WoodStairsBlock(POISE_PLANKS.get().defaultBlockState(), EEProperties.POISE_WOOD), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> POISE_FENCE           = HELPER.createFuelBlock("poise_fence", () -> new WoodFenceBlock(EEProperties.POISE_WOOD), 300, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> POISE_FENCE_GATE      = HELPER.createFuelBlock("poise_fence_gate", () -> new WoodFenceGateBlock(EEProperties.POISE_WOOD), 300, CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> POISE_PRESSURE_PLATE 	= HELPER.createBlock("poise_pressure_plate", () -> new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, EEProperties.POISE_WOOD), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> POISE_BUTTON          = HELPER.createBlock("poise_button", () -> new AbnormalsWoodButtonBlock(EEProperties.getPoiseWood(false, true)), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> POISE_TRAPDOOR        = HELPER.createBlock("poise_trapdoor", () -> new WoodTrapDoorBlock(EEProperties.POISE_WOOD_NOT_SOLID), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> POISE_VERTICAL_PLANKS = HELPER.createCompatBlock("quark", "vertical_poise_planks", () -> new Block(EEProperties.POISE_WOOD), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> POISE_VERTICAL_SLAB   = HELPER.createCompatFuelBlock("quark", "poise_vertical_slab", () -> new VerticalSlabBlock(EEProperties.POISE_WOOD), 150, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> POISE_BOOKSHELF       = HELPER.createCompatFuelBlock("quark", "poise_bookshelf", () -> new BookshelfBlock(Properties.copy(Blocks.BOOKSHELF)), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> POISE_LADDER          = HELPER.createCompatFuelBlock("quark", "poise_ladder", () -> new AbnormalsLadderBlock(Properties.copy(Blocks.LADDER)), 300, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> POISE_BEEHIVE         = HELPER.createCompatBlock("buzzier_bees", "poise_beehive", () -> new AbnormalsBeehiveBlock(Properties.copy(Blocks.BEEHIVE)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> STRIPPED_POISE_POST 	= HELPER.createCompatFuelBlock("quark", "stripped_poise_post", () -> new WoodPostBlock(EEProperties.POISE_WOOD), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> POISE_POST 			= HELPER.createCompatFuelBlock("quark", "poise_post", () -> new WoodPostBlock(STRIPPED_POISE_POST, EEProperties.POISE_WOOD), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final Pair<RegistryObject<AbnormalsStandingSignBlock>, RegistryObject<AbnormalsWallSignBlock>> POISE_SIGN  = HELPER.createSignBlock("poise", MaterialColor.TERRACOTTA_PURPLE);
	public static final Pair<RegistryObject<AbnormalsChestBlock>, RegistryObject<AbnormalsTrappedChestBlock>> 	 POISE_CHEST = HELPER.createCompatChestBlocks("quark", "poise", MaterialColor.TERRACOTTA_PURPLE);

	public static final RegistryObject<Block> BOLLOOM_BUD           = HELPER.createBlockWithISTER("bolloom_bud", () -> new BolloomBudBlock(EEProperties.getPoiseWood(true, false)), () -> bolloomBudISTER(), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> PUFFBUG_HIVE          = HELPER.createBlockWithISTER("puffbug_hive", () -> new PuffBugHiveBlock(EEProperties.getPuffBugHive(true)), () -> puffbugHiveISTER(), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> HIVE_HANGER 			= HELPER.createBlockNoItem("hive_hanger", () -> new PuffbugHiveHangerBlock(EEProperties.getPuffBugHive(false)));
	public static final RegistryObject<Block> BOLLOOM_PARTICLE      = HELPER.createBlockNoItem("bolloom_particle", () -> new Block(EEProperties.getPoiseWood(false, true)));
	public static final RegistryObject<Block> BOOF_BLOCK            = HELPER.createBlock("boof_block", () -> new BoofBlock(EEProperties.BOOF_BLOCK), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> BOOF_BLOCK_DISPENSED  = HELPER.createBlockNoItem("dispensed_boof_block", () -> new DispensedBoofBlock(EEProperties.BOOF_BLOCK.noCollission().noOcclusion().strength(-1, 3600000.0F)));
	public static final RegistryObject<Block> BOLLOOM_CRATE			= HELPER.createCompatBlock("quark", "bolloom_crate", () -> new Block(EEProperties.BOLLOOM_CRATE), CreativeModeTab.TAB_DECORATIONS);

	/*
	 * Vibra Jungle(Unused)
	 */
	public static Block FRISBLOOM_STEM    = new FrisbloomStemBlock(EEProperties.FRISBLOOM_STEM).setRegistryName(EndergeticExpansion.MOD_ID, "frisbloom_stem");
	public static Block FRISBLOOM_BUD     = new FrisbloomBudBlock(EEProperties.FRISBLOOM_BUD.noCollission()).setRegistryName(EndergeticExpansion.MOD_ID, "frisbloom_seeds");

	/*
	 * Misc
	 */
	public static final RegistryObject<Block> EUMUS                     = HELPER.createBlock("eumus", () -> new EumusBlock(EEProperties.EUMUS), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> EUMUS_BRICKS              = HELPER.createBlock("eumus_bricks", () -> new Block(EEProperties.EUMUS_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CRACKED_EUMUS_BRICKS      = HELPER.createBlock("cracked_eumus_bricks", () -> new Block(EEProperties.EUMUS_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHISELED_EUMUS_BRICKS     = HELPER.createBlock("chiseled_eumus_bricks", () -> new Block(EEProperties.EUMUS_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> EUMUS_BRICK_SLAB          = HELPER.createBlock("eumus_brick_slab", () -> new SlabBlock(EEProperties.EUMUS_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> EUMUS_BRICK_STAIRS        = HELPER.createBlock("eumus_brick_stairs", () -> new StairBlock(() -> EUMUS_BRICKS.get().defaultBlockState(), EEProperties.EUMUS_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> EUMUS_BRICK_WALL 			= HELPER.createBlock("eumus_brick_wall", () -> new WallBlock(EEProperties.EUMUS_BRICKS), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> EUMUS_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "eumus_brick_vertical_slab", () -> new VerticalSlabBlock(EEProperties.EUMUS_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> POTTED_POISE_BUSH         = HELPER.createBlockNoItem("potted_poise_bush", () -> new FlowerPotBlock(POISE_BUSH.get(), Properties.copy(Blocks.POTTED_PINK_TULIP)));
	public static final RegistryObject<Block> POTTED_TALL_POISE_BUSH    = HELPER.createBlockNoItem("potted_tall_poise_bush", () -> new FlowerPotBlock(TALL_POISE_BUSH.get(), Properties.copy(Blocks.POTTED_PINK_TULIP)));

	public static final RegistryObject<Block> ACIDIAN_LANTERN   		= HELPER.createBlock("acidian_lantern", () ->  new AcidianLanternBlock(EEProperties.ACIDIAN_LANTERN), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> CRYSTAL_HOLDER			= HELPER.createBlock("crystal_holder", () -> new Block(EEProperties.MYSTICAL_OBSIDIAN), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> MYSTICAL_OBSIDIAN         = HELPER.createBlock("mystical_obsidian", () -> new Block(EEProperties.MYSTICAL_OBSIDIAN), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> MYSTICAL_OBSIDIAN_WALL    = HELPER.createBlock("mystical_obsidian_wall", () -> new WallBlock(EEProperties.MYSTICAL_OBSIDIAN), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> MYSTICAL_OBSIDIAN_RUNE    = HELPER.createBlock("mystical_obsidian_rune", () -> new RotatableBlock(EEProperties.MYSTICAL_OBSIDIAN), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> MYSTICAL_OBSIDIAN_ACTIVATION_RUNE 			= HELPER.createBlock("mystical_obsidian_activation_rune", () -> new RotatableBlock(EEProperties.MYSTICAL_OBSIDIAN), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> ACTIVATED_MYSTICAL_OBSIDIAN_ACTIVATION_RUNE 	= HELPER.createBlock("activated_mystical_obsidian_activation_rune", () -> new RotatableBlock(EEProperties.MYSTICAL_OBSIDIAN.lightLevel(state -> 5)), CreativeModeTab.TAB_DECORATIONS);

	public static final RegistryObject<Block> ENDER_FIRE 		= HELPER.createBlockNoItem("ender_fire", () -> new EnderFireBlock(Properties.copy(Blocks.FIRE)));
	public static final RegistryObject<Block> ENDER_CAMPFIRE	= HELPER.createBlock("ender_campfire", () -> new EnderCampfireBlock(Block.Properties.copy(Blocks.CAMPFIRE)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> ENDER_LANTERN		= HELPER.createBlock("ender_lantern", () -> new Lantern(Block.Properties.copy(Blocks.LANTERN)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> ENDER_WALL_TORCH	= HELPER.createBlockNoItem("ender_wall_torch", () -> new EnderWallTorchBlock(Block.Properties.copy(Blocks.TORCH)));
	public static final RegistryObject<Block> ENDER_TORCH		= HELPER.createWallOrFloorBlock("ender_torch", () -> new EnderTorchBlock(Block.Properties.copy(Blocks.TORCH)), ENDER_WALL_TORCH, CreativeModeTab.TAB_DECORATIONS);

	public static final RegistryObject<Block> CHISELED_END_STONE_BRICKS = HELPER.createBlock("chiseled_end_stone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE_BRICKS)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CRACKED_END_STONE_BRICKS  = HELPER.createBlock("cracked_end_stone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE_BRICKS)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CRACKED_PURPUR_BLOCK 		= HELPER.createBlock("cracked_purpur_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.PURPUR_BLOCK)), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> EUMUS_POISMOSS_PATH = HELPER.createBlock("eumus_poismoss_path", () -> new PoismossPathBlock(EUMUS, EEProperties.EUMUS_POISMOSS_PATH), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> POISMOSS_PATH = HELPER.createBlock("poismoss_path", () -> new PoismossPathBlock(() -> Blocks.END_STONE, EEProperties.POISMOSS_PATH), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> EETLE_EGG = HELPER.createBlock("eetle_egg", () -> new EetleEggBlock(EEProperties.EETLE_EGG), CreativeModeTab.TAB_DECORATIONS);

	@OnlyIn(Dist.CLIENT)
	private static Callable<BlockEntityWithoutLevelRenderer> bolloomBudISTER() {
		return () -> new EETileEntityItemRenderer<BlockEntity>(BolloomBudTileEntity::new);
	}

	@OnlyIn(Dist.CLIENT)
	private static Callable<BlockEntityWithoutLevelRenderer> puffbugHiveISTER() {
		return () -> new EETileEntityItemRenderer<BlockEntity>(PuffBugHiveTileEntity::new);
	}
}