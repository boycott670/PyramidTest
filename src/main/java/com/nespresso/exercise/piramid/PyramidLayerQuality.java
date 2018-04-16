package com.nespresso.exercise.piramid;

enum PyramidLayerQuality
{
  LOW
  {
    @Override
    char print()
    {
      return 'V';
    }
  },
  HIGH
  {
    @Override
    char print()
    {
      return 'X';
    }
  };
  
  abstract char print();
  
  static PyramidLayerQuality ofFactor(final int factor)
  {
    return factor < 2 ? LOW : HIGH;
  }
}
