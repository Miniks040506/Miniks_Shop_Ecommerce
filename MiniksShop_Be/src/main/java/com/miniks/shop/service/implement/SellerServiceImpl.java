package com.miniks.shop.service.implement;

import com.miniks.shop.config.JwtProvider;
import com.miniks.shop.domain.AccountStatus;
import com.miniks.shop.domain.UserRole;
import com.miniks.shop.entity.Address;
import com.miniks.shop.entity.Seller;
import com.miniks.shop.repository.AddressRepository;
import com.miniks.shop.repository.SellerRepository;
import com.miniks.shop.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;

    @Override
    public Seller getSellerProfile(String jwtToken) throws Exception {

        String email = jwtProvider.getEmailFromJwtToken(jwtToken);

        return this.getSellerByEmail(email);
    }

    @Override
    public Seller createSeller(Seller seller) throws Exception {

        Seller sellerExist = sellerRepository.findByEmail(seller.getEmail());

        if (sellerExist != null) {
            throw new Exception("Seller already exist, used different email");
        }

        Address address = addressRepository.save(seller.getAddress());

        Seller newSeller = new Seller();
        newSeller.setEmail(seller.getEmail());
        newSeller.setPassword(passwordEncoder.encode(seller.getPassword()));
        newSeller.setSellerName(seller.getSellerName());
        newSeller.setAddress(seller.getAddress());
        newSeller.setTaxCode(seller.getTaxCode());
        newSeller.setRole(UserRole.ROLE_SELLER);
        newSeller.setMobile(seller.getMobile());
        newSeller.setBankDetails(seller.getBankDetails());
        newSeller.setBusinessDetails(seller.getBusinessDetails());

        return sellerRepository.save(newSeller);
    }

    @Override
    public Seller getSellerById(Long id) throws Exception {

//        Optional<Seller> seller = sellerRepository.findById(id);
//
//        if (seller.isPresent()) {
//            return seller.get();
//        }
//        throw new Exception("Seller not found with id: " + id);

        return sellerRepository.findById(id)
                .orElseThrow(() ->
                        new Exception("Seller not found with id - " + id));
    }

    @Override
    public Seller getSellerByEmail(String email) throws Exception {

        Seller seller = sellerRepository.findByEmail(email);

        if (seller == null) {
            throw new Exception("Seller not found with email -" + email);
        }

        return seller;
    }

    @Override
    public List<Seller> getAllSellers(AccountStatus status) {

        return sellerRepository.findByAccountStatus(status);
    }

    @Override
    public Seller updateSeller(Long id, Seller seller) throws Exception {

        Seller existingSeller = this.getSellerById(id);

        if (seller.getSellerName() != null) {
            existingSeller.setSellerName(seller.getSellerName());
        }

        if (seller.getMobile() != null) {
            existingSeller.setMobile(seller.getMobile());
        }

        if (seller.getEmail() != null) {
            existingSeller.setEmail(seller.getEmail());
        }

        if (seller.getBusinessDetails() != null
                && seller.getBusinessDetails().getBusinessName() != null) {

            existingSeller.getBusinessDetails().setBusinessName(
                    seller.getBusinessDetails().getBusinessName());
        }

        if (seller.getBankDetails() != null
                && seller.getBankDetails().getAccountHolderName() != null
                && seller.getBankDetails().getBankCode() != null
                && seller.getBankDetails().getAccountNumber() != null
                && seller.getBankDetails().getBankName() != null) {

            existingSeller.getBankDetails().setBankName(
                    seller.getBankDetails().getBankName());

            existingSeller.getBankDetails().setBankCode(
                    seller.getBankDetails().getBankCode());

            existingSeller.getBankDetails().setAccountNumber(
                    seller.getBankDetails().getAccountNumber());

            existingSeller.getBankDetails().setAccountHolderName(
                    seller.getBankDetails().getAccountHolderName());
        }

        if (seller.getAddress() != null
                && seller.getAddress().getAddress() != null
                && seller.getAddress().getMobile() != null
                && seller.getAddress().getCity() != null
                && seller.getAddress().getState() != null) {

            existingSeller.getAddress().setAddress(seller.getAddress().getAddress());
            existingSeller.getAddress().setCity(seller.getAddress().getCity());
            existingSeller.getAddress().setState(seller.getAddress().getState());
            existingSeller.getAddress().setMobile(seller.getAddress().getMobile());
            existingSeller.getAddress().setPinCode(seller.getAddress().getPinCode());
        }

        if (seller.getTaxCode() != null) {
            existingSeller.setTaxCode(seller.getTaxCode());
        }

        return sellerRepository.save(existingSeller);
    }

    @Override
    public void deleteSeller(Long id) throws Exception {

        Seller seller = getSellerById(id);

        sellerRepository.delete(seller);

    }

    @Override
    public Seller verifyEmail(String email, String otp) throws Exception {

        Seller seller = getSellerByEmail(email);

        seller.setEmailVerified(true);

        return sellerRepository.save(seller);
    }

    @Override
    public Seller updateSellerAccountStatus(Long sellerId, AccountStatus status) throws Exception {

        Seller seller = getSellerById(sellerId);

        seller.setAccountStatus(status);

        return sellerRepository.save(seller);
    }
}
