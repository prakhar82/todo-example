package com.boc.persistence.service;

import com.backbase.persistence.rest.spec.v1.changeFormatter.ChangeFormatterPostRequestBody;
import com.backbase.persistence.rest.spec.v1.changeFormatter.ChangeFormatterPostResponseBody;
import org.springframework.stereotype.Service;

@Service
public interface ChangeFormatterService {
    ChangeFormatterPostResponseBody changeFormatter(ChangeFormatterPostRequestBody data);
}
