package com.nespresso.exercise.piramid;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.PrimitiveIterator.OfInt;

final class Pyramid
{
  private final List<PyramidLayer> layers = new ArrayList<>();
  
  void addLayer(final String layerDescription)
  {
    final OfInt tokens = Arrays.stream(layerDescription.split("\\s+")).filter(token -> token.matches("\\d+")).mapToInt(Integer::parseInt).iterator();
    
    final int slaves = tokens.nextInt();
    final int anks = tokens.nextInt();
    
    final PyramidLayer toAdd = new PyramidLayer(slaves, anks);
    
    if (toAdd.willCollapseWithExisting(new ArrayDeque<>(layers).pollLast()))
    {
      layers.set(layers.size() - 1, toAdd);
    }
    else
    {
      layers.add(toAdd);
    }
  }
  
  String print()
  {
    final Collection<String> report = new ArrayList<>();
    
    for (int pyramidLayerIndex = 0 ; pyramidLayerIndex < layers.size() ; pyramidLayerIndex ++)
    {
      if (pyramidLayerIndex > 0)
      {
        report.add(layers.get(pyramidLayerIndex).print(layers.get(0), layers.get(pyramidLayerIndex - 1)));
      }
      else
      {
        report.add(layers.get(pyramidLayerIndex).print(null, null));
      }
    }
    
    return String.join("\n", (Iterable<String>)(() -> new ArrayDeque<>(report).descendingIterator()));
  }
}
