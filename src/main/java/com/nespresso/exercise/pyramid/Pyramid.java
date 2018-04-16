package com.nespresso.exercise.pyramid;

import java.util.LinkedList;
import java.util.Stack;

import com.nespresso.exercise.pyramid.parsers.DefaultPyramidLayerParser;
import com.nespresso.exercise.pyramid.parsers.PyramidLayerParser;
import com.nespresso.exercise.pyramid.printers.DefaultPyramidPrinter;
import com.nespresso.exercise.pyramid.printers.PyramidPrinter;

public final class Pyramid
{
  private final PyramidLayerParser pyramidLayerParser = new DefaultPyramidLayerParser();
  private final PyramidPrinter pyramidPrinter = new DefaultPyramidPrinter();

  private final Stack<PyramidLayer> layers = new Stack<>();

  public void addLayer(final String layer)
  {
    final PyramidLayer layerToAdd = pyramidLayerParser.parse(layer);
    
    if (layers.isEmpty())
    {
      layers.push(layerToAdd);
    }
    else
    {
       final PyramidLayer topLayer = layers.peek();
       
       if (layerToAdd.collapseWithExisting(topLayer))
       {
         layers.pop();
       }
       
       layers.push(layerToAdd);
    }
  }

  public String print()
  {
    return pyramidPrinter.print(new LinkedList<>(layers));
  }
}
