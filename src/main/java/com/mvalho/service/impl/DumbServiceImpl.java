package com.mvalho.service.impl;

import org.springframework.stereotype.Service;

import com.mvalho.service.DumbService;

@Service
public class DumbServiceImpl implements DumbService {

	@Override
	public String doSomething(String string) {
		return string.toUpperCase();
	}

}
