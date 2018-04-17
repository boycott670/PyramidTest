package com.nespresso.exercise.piramid;

import java.util.Collections;
import java.util.Comparator;

final class PyramidLayer
{
  private static final String UNDERSCORE = "_";
  private static final String SPACE = " ";
  
  private final int size;
  private final boolean isHighQuality;
  
  PyramidLayer(int size, boolean isHighQuality)
  {
    this.size = size;
    this.isHighQuality = isHighQuality;
  }

  private String printBlock()
  {
    return isHighQuality ? "X" : "V";
  }
  
  private int byHalf(final int number)
  {
    return number / 2;
  }
  
  String print(final PyramidLayer baseLayer, final PyramidLayer previousLayer)
  {
    final int numberOfUnderscores = previousLayer.size - size;
    final int numberOfSpaces = baseLayer.size - (numberOfUnderscores + size);
    
    final String underscores = String.join("", Collections.nCopies(byHalf(numberOfUnderscores), UNDERSCORE));
    final String spaces = String.join("", Collections.nCopies(byHalf(numberOfSpaces), SPACE));
    final String blocks = String.join("", Collections.nCopies(size, printBlock()));
    
    return String.format("%s%s%s%s%s", spaces, underscores, blocks, underscores, spaces);
  }
  
  boolean willCollapseWith(final PyramidLayer existingLayer)
  {
    return Comparator.<PyramidLayer, Integer>comparing(layer -> layer.size).<Boolean>thenComparing(layer -> layer.isHighQuality).compare(this, existingLayer) >= 0;
  }
}
