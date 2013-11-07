package com.omp.dev.common.service;

import javax.servlet.http.HttpSession;

public interface AuthenticationService {

	boolean isLogined(HttpSession session);

}
