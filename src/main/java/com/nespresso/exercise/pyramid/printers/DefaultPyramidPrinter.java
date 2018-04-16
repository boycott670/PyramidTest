package com.nespresso.exercise.pyramid.printers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import com.nespresso.exercise.pyramid.PyramidLayer;

public final class DefaultPyramidPrinter implements PyramidPrinter
{

  private String repeat(final char character, final int times)
  {
    return String.join("", Collections.nCopies(times, String.valueOf(character)));
  }

  @Override
  public String print(Queue<PyramidLayer> layers)
  {
    final List<String> printLines = new ArrayList<>();

    PyramidLayer previousLayer = null;
    PyramidLayer currentLayer = null;

    int baseSize = 0;

    while ((currentLayer = layers.poll()) != null)
    {
      if (previousLayer == null)
      {
        baseSize = currentLayer.size();

        printLines.add(repeat(currentLayer.charToPrint(), baseSize));
      }
      else
      {
        final int numberOfUnderscores = previousLayer.size() - currentLayer.size();
        final int numberOfSpaces = baseSize - (numberOfUnderscores + currentLayer.size());

        printLines.add(String.format("%s%s%s%s%s", repeat(' ', numberOfSpaces / 2), repeat('_', numberOfUnderscores / 2),
            repeat(currentLayer.charToPrint(), currentLayer.size()), repeat('_', numberOfUnderscores / 2),
            repeat(' ', numberOfSpaces / 2)));
      }

      previousLayer = currentLayer;
    }

    Collections.reverse(printLines);

    return printLines.stream()
        .collect(Collectors.joining("\n"));
  }

}
