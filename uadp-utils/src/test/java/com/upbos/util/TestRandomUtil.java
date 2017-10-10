package com.upbos.util;

public class TestRandomUtil {
	public static void main(String[] args) {
		System.out.println(RandomUtils.get32UUID());
		System.out.println(RandomUtils.getUUID());
		for(int i = 0; i < 100; i++) {
			System.out.println(RandomUtils.getCharacterAndNumber(10));
		}
		
		for(int i = 0; i < 100; i++) {
			System.out.println(RandomUtils.getNumber(6));
		}
	}
}
