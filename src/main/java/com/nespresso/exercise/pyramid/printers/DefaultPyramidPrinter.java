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
        baseSize = currentLayer.numberOfCharsToDisplay();

        printLines.add(repeat(currentLayer.charToPrint(), baseSize));
      }
      else
      {
        final int numberOfUnderscores = previousLayer.numberOfCharsToDisplay() - currentLayer.numberOfCharsToDisplay();
        final int numberOfSpaces = baseSize - (numberOfUnderscores + currentLayer.numberOfCharsToDisplay());

        printLines.add(String.format("%s%s%s%s%s", repeat(' ', numberOfSpaces / 2), repeat('_', numberOfUnderscores / 2),
            repeat(currentLayer.charToPrint(), currentLayer.numberOfCharsToDisplay()), repeat('_', numberOfUnderscores / 2),
            repeat(' ', numberOfSpaces / 2)));
      }

      previousLayer = currentLayer;
    }

    Collections.reverse(printLines);

    return printLines.stream()
        .collect(Collectors.joining("\n"));
  }

}
