package com.nespresso.exercise.pyramid.printers;

import java.util.Queue;

import com.nespresso.exercise.pyramid.PyramidLayer;

public interface PyramidPrinter
{
  String print(final Queue<PyramidLayer> layers);
}
