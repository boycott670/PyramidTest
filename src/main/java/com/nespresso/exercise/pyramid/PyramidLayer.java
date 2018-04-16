package com.nespresso.exercise.pyramid;

public class PyramidLayer
{
  public static enum Type
  {
    X, V;

    public char toChar()
    {
      return name().charAt(0);
    }
  }

  private final int slaves;
  private final int anks;
  private final Type type;

  private PyramidLayer(int slaves, int anks)
  {
    this.slaves = slaves;
    this.anks = anks;

    type = this.anks / size() < 2 ? Type.V : Type.X;
  }

  public int size()
  {
    return slaves / 50;
  }

  public char charToPrint()
  {
    return type.toChar();
  }

  public int numberOfCharsToDisplay()
  {
    return size();
  }

  public boolean collapseWithExisting(PyramidLayer existingLayer)
  {
    return size() >= existingLayer.size() && type == existingLayer.type;
  }

  public static PyramidLayer of(final int slaves, final int anks)
  {
    return new PyramidLayer(slaves, anks);
  }
}
