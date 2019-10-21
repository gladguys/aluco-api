package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.Call;

public interface CallService {

	Call getById(Long id);
	Call update(Call call);
	void deleteById(Long id);

}
