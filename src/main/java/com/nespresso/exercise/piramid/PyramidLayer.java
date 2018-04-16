package com.nespresso.exercise.piramid;

import java.util.Collections;

final class PyramidLayer
{
  private final int slaves;
  private final int anks;
  private final PyramidLayerQuality quality;
  
  private PyramidLayer(int slaves, int anks)
  {
    this.slaves = slaves;
    this.anks = anks;
    
    quality = PyramidLayerQuality.ofFactor(this.anks / size());
  }
  
  private int size()
  {
    return slaves / 50;
  }
  
  public String print(final PyramidLayer baseLayer, final PyramidLayer previousLayer)
  {
    final int numberOfUnderscores = previousLayer.size() - size();
    final int numberOfSpaces = baseLayer.size() - (numberOfUnderscores + size());
    
    final String underscores = String.join("", Collections.nCopies(numberOfUnderscores / 2, "_"));
    final String spaces = String.join("", Collections.nCopies(numberOfSpaces / 2, " "));
    
    return String.format("%s%s%s%s%s", spaces, underscores, String.join("", Collections.nCopies(size(), String.valueOf(quality.print()))), underscores, spaces);
  }
  
  public boolean willCollapseWith(final PyramidLayer otherLayer)
  {
    return size() >= otherLayer.size() && quality == otherLayer.quality;
  }

  public static PyramidLayer of(final Integer slaves, final Integer anks)
  {
    return new PyramidLayer(slaves, anks);
  }
}
