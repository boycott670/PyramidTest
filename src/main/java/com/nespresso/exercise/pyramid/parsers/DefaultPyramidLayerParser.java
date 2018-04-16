package com.nespresso.exercise.pyramid.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nespresso.exercise.pyramid.PyramidLayer;

public final class DefaultPyramidLayerParser implements PyramidLayerParser
{

  @Override
  public PyramidLayer parse(String layer)
  {
    final Matcher matcher = Pattern.compile("^(\\d+) Slaves, (\\d+) Anks$")
        .matcher(layer);

    if (!matcher.find())
    {
      throw new IllegalArgumentException("Invalid layer structure");
    }

    return PyramidLayer.of(Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(2)));
  }

}
