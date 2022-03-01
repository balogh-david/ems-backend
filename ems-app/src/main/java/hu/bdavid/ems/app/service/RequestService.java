package hu.bdavid.ems.app.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import hu.bdavid.ems.app.model.Request;

@Service
@RequiredArgsConstructor
public class RequestService extends BaseService<Request, String> {
}
