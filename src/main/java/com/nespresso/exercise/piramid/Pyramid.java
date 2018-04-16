package com.nespresso.exercise.piramid;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class Pyramid
{
  private static final Function<? super String, ? extends PyramidLayer> pyramidLayerParser = pyramidLayer ->
  {
    final Matcher matcher = Pattern.compile("^(\\d+) Slaves, (\\d+) Anks$").matcher(pyramidLayer);

    if (!matcher.find())
    {
      throw new IllegalArgumentException("Invalid layer structure");
    }

    return PyramidLayer.of(Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(2)));
  };
  
  private final Deque<PyramidLayer> layers = new ArrayDeque<>();
  
  public void addLayer(final String pyramidLayer)
  {
    final PyramidLayer layer = pyramidLayerParser.apply(pyramidLayer);
    
    if (!layers.isEmpty() && layer.compareTo(layers.peekLast()) >= 0)
    {
      layers.removeLast();
    }
    
    layers.addLast(layer);
  }
  
  public String print()
  {
    final Deque<? extends PyramidLayer> layers = new ArrayDeque<>(this.layers);
    final List<String> printContent = new ArrayList<>();
    
    PyramidLayer baseLayer = null, previousLayer = null, currentLayer;
    
    while ((currentLayer = layers.pollFirst()) != null)
    {
      if (previousLayer == null)
      {
        baseLayer = currentLayer;
      }
      
      printContent.add(currentLayer.print(baseLayer, previousLayer == null ? currentLayer : previousLayer));
      
      previousLayer = currentLayer;
    }
    
    Collections.reverse(printContent);
    
    return printContent.stream().collect(Collectors.joining("\n"));
  }
}
