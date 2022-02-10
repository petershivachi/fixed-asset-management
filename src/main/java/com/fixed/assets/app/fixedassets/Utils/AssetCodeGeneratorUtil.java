package com.fixed.assets.app.fixedassets.Utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AssetCodeGeneratorUtil {
 public  String generateAssetCode(){
  int leftLimit = 97; // letter 'a'
  int rightLimit = 122; // letter 'z'
  int targetStringLength = 6;
  Random random = new Random();
  StringBuilder buffer = new StringBuilder(targetStringLength);
  for (int i = 0; i < targetStringLength; i++) {
   int randomLimitedInt = leftLimit + (int)
      (random.nextFloat() * (rightLimit - leftLimit + 1));
   buffer.append((char) randomLimitedInt);
  }

  return buffer.toString();

 }
}
