package com.nespresso.exercise.piramid;

import java.util.Collections;
import java.util.function.BiFunction;

final class PyramidLayer
{
  private final int size;
  private final char repr;
  
  PyramidLayer(final int slaves, final int anks)
  {
    size = slaves / 50;
    repr = anks / size >= 2 ? 'X' : 'V';
  }
  
  String print(final PyramidLayer base, final PyramidLayer previous)
  {
    final BiFunction<Character, Integer, String> repeatCharacter = (c, n) -> String.join("", Collections.nCopies(n, String.valueOf(c)));
    
    final String repr = repeatCharacter.apply(this.repr, size);
    
    final int numberOfUnderscores = previous != null ? (previous.size - size) : 0;
    
    final String underscores = repeatCharacter.apply('_', numberOfUnderscores / 2);
    
    final String spaces = repeatCharacter.apply(' ', previous != null ? (base.size - (size + numberOfUnderscores)) / 2 : 0);
    
    return String.format("%s%s%s%s%s", spaces, underscores, repr, underscores, spaces);
  }
  
  boolean willCollapseWithExisting(final PyramidLayer existing)
  {
    return existing != null && size >= existing.size && repr == existing.repr;
  }
}
