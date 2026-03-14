package com.miniks.shop.service;

import com.miniks.shop.entity.Seller;
import com.miniks.shop.entity.SellerReport;

public interface SellerReportService {

    SellerReport getSellerReport(Seller seller);

    SellerReport updateSellerReport(SellerReport sellerReport);

}
