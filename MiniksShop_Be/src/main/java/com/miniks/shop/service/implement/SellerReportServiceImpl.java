package com.miniks.shop.service.implement;

import com.miniks.shop.entity.Seller;
import com.miniks.shop.entity.SellerReport;
import com.miniks.shop.repository.SellerReportRepository;
import com.miniks.shop.service.SellerReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerReportServiceImpl implements SellerReportService {

    private final SellerReportRepository sellerReportRepository;

    @Override
    public SellerReport getSellerReport(Seller seller) {

        SellerReport report = sellerReportRepository.findBySellerId(seller.getId());

        if (report == null) {
            SellerReport sellerReport = new SellerReport();
            sellerReport.setSeller(seller);
            return sellerReportRepository.save(sellerReport);
        }

        return report;
    }

    @Override
    public SellerReport updateSellerReport(SellerReport sellerReport) {

        return sellerReportRepository.save(sellerReport);
    }
}
