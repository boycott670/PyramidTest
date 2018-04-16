package com.nespresso.exercise.piramid;

enum PyramidLayerQuality
{
  HIGH
  {
    @Override
    char print()
    {
      return 'X';
    }
  },
  LOW
  {
    @Override
    char print()
    {
      return 'V';
    }
  };
  
  abstract char print();
  
  static PyramidLayerQuality ofFactor(final int factor)
  {
    return factor < 2 ? LOW : HIGH;
  }
}
