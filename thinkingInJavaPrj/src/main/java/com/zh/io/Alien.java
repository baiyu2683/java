package com.zh.io;

import java.io.Serializable;

/**
 * A serializable class
 * Created by zh on 2017-03-14.
 */
public class Alien implements Serializable {
    
//  @Override
//  public String toString() {
//      return "Alien";
//  }
  
  // 防止反序列化
//  private Object readResolve() throws ObjectStreamException {
//      throw new UnsupportedOperationException();
//  }
}
