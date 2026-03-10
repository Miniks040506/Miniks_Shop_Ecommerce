package com.miniks.shop.controller;

import com.miniks.shop.config.JwtProvider;
import com.miniks.shop.domain.AccountStatus;
import com.miniks.shop.entity.Seller;
import com.miniks.shop.entity.VerificationCode;
import com.miniks.shop.exception.SellerException;
import com.miniks.shop.repository.VerificationCodeRepository;
import com.miniks.shop.request.LoginRequest;
import com.miniks.shop.response.ApiResponse;
import com.miniks.shop.response.AuthResponse;
import com.miniks.shop.service.AuthService;
import com.miniks.shop.service.EmailService;
import com.miniks.shop.service.SellerService;
import com.miniks.shop.utils.OtpUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers")
public class SellerController {

    private final SellerService sellerService;
    private final VerificationCodeRepository verificationCodeRepository;
    private final AuthService authService;
    private final EmailService emailService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(
            @RequestBody LoginRequest request) throws Exception {

//        String otp = request.getOtp();
        String email = request.getEmail();

//        VerificationCode verificationCode = verificationCodeRepository.findByEmail(email);
//
//        if (verificationCode == null
//                || !verificationCode.getOtp().equals(request.getOtp())) {
//            throw new Exception("Wrong otp...");
//        }

        request.setEmail("seller_" + email);

        AuthResponse authResponse = authService.SigningAccount(request);

        return ResponseEntity.ok(authResponse);
    }

    @PatchMapping("/verify/{otp}")
    public ResponseEntity<Seller> verifySellerEmailHandler
            (@PathVariable String otp) throws Exception {

        VerificationCode verificationCode = verificationCodeRepository.findByOtp(otp);

        if (verificationCode == null || !verificationCode.getOtp().equals(otp)) {
            throw new Exception("Wrong otp...");
        }

        Seller seller = sellerService.verifyEmail(verificationCode.getEmail(), otp);

        return ResponseEntity.ok(seller);
    }

    @PostMapping()
    public ResponseEntity<Seller> createSellerHandler(
            @RequestBody Seller seller) throws SellerException, MessagingException {

        Seller savedSeller = sellerService.createSeller(seller);

        String otp = OtpUtil.generateOtpCode();

//        VerificationCode verificationCode = verificationService;

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(seller.getEmail());
        verificationCodeRepository.save(verificationCode);

        String subject = "Miniks Shop Email verification code";
        String text = "Welcome to Miniks Shop, verify your account using this link ";
        String frontend_url = "http://localhost:3000/verify-seller/";

        emailService.sendVerificationOtpEmail(seller.getEmail(),
                verificationCode.getOtp(), subject, text + frontend_url);

        return new ResponseEntity<>(savedSeller, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerHandler(@PathVariable Long id) throws SellerException {

        Seller seller = sellerService.getSellerById(id);

        return ResponseEntity.ok(seller);
    }

    @GetMapping("/profile")
    public ResponseEntity<Seller> getSellerProfileHandler(
            @RequestHeader("Authorization") String jwtToken
    ) throws SellerException {

//        String email = jwtProvider.getEmailFromJwtToken(jwtToken);
//
//        Seller seller = sellerService.getSellerByEmail(email);

        Seller seller = sellerService.getSellerProfile(jwtToken);

        return ResponseEntity.ok(seller);
    }

//    @GetMapping("/report")
//    public ResponseEntity<SellerReport> getSellerReportHandler(
//            @RequestHeader("Authorization")  String jwtToken
//    ) throws Exception {
//
////        String email = jwtProvider.getEmailFromJwtToken(jwtToken);
////
////        Seller seller = sellerService.getSellerByEmail(email);
//
//        Seller seller = sellerService.getSellerProfile(jwtToken);
//

    /// /        SellerReport report =
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
    @GetMapping
    public ResponseEntity<List<Seller>> getAllSellersHandler(
            @RequestParam(required = false) AccountStatus status
    ) {

        List<Seller> sellers = sellerService.getAllSellers(status);

        return ResponseEntity.ok(sellers);
    }

    @PatchMapping
    public ResponseEntity<Seller> updateSellerHandler(
            @RequestHeader("Authorization") String jwtToken,
            @RequestBody Seller seller
    ) throws SellerException {

        Seller profile = sellerService.getSellerProfile(jwtToken);

        Seller updatedSeller = sellerService.updateSeller(profile.getId(), seller);

        return ResponseEntity.ok(updatedSeller);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteSellerHandler(@PathVariable Long id) throws SellerException {

        sellerService.deleteSeller(id);

        return ResponseEntity.noContent().build();
    }

}
