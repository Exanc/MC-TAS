package dev.exanc.technoalchemy.worldgen.util;

import java.util.List;

import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

public class OrePlacement {

  public static List<PlacementModifier> orePlacements (
    PlacementModifier countPlacement,
    PlacementModifier heightRange
  ) {
    return List.of(countPlacement, InSquarePlacement.spread(), heightRange, BiomeFilter.biome());
  }

  public static List<PlacementModifier> commonOrePlacements (
    int count,
    PlacementModifier heightRange
  ) {
    return orePlacements(CountPlacement.of(count), heightRange);
  }

  public static List<PlacementModifier> rareOrePlacements (
    int chance,
    PlacementModifier heightRange
  ) {
    return orePlacements(RarityFilter.onAverageOnceEvery(chance), heightRange);
  }
}
