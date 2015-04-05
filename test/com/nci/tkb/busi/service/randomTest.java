package com.nci.tkb.busi.service;

import java.util.Random;

import org.junit.Test;

public class randomTest
{
	private final Random random = new Random();
	@Test
	public void test() {
		System.out.println(random.nextInt(1000000));
	}
}
