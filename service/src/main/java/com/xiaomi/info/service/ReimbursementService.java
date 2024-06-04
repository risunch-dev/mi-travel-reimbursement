package com.xiaomi.info.service;

import com.xiaomi.info.model.Reimbursement;

public interface ReimbursementService {
    void submit(Reimbursement reimBurseMent);

    void changeDetail(Long id, String item, String attachment, Integer amount);

    void deleteById(Long id);

    Reimbursement getById(Long id);
}
