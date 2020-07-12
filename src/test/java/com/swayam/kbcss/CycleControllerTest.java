package com.swayam.kbcss;

import com.swayam.kbcss.controller.CycleController;
import com.swayam.kbcss.request.Request;
import com.swayam.kbcss.response.Response;
import com.swayam.kbcss.service.CycleService;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.CompletableFutureAssert;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = {CycleController.class,CycleService.class})
@SpringBootTest
public class CycleControllerTest {
    @Autowired
    private CycleService cycleService;

    @Autowired
    private CycleController controller;

    @Autowired
     private static Request request ;

    @BeforeClass
    public static void init(){
     request=new Request();
        request.setType("TUBELESS");
        request.setDateOfPricing(LocalDate.of(2016,11,30));
        request.setCycleComponents( Arrays.asList("RIM", "TUBE", "TYRE"));
    }
    //test case

    @Test
    public void calculatePriceTest1() throws Exception {
        final CompletableFuture<List<Response>> response =controller.calculatePrice(Arrays.asList(request,request));
        Assert.assertFalse("false", response.get().isEmpty());
    }
    @Test
    public void calculatePriceTest2() throws Exception {
        // Mockito.when(controller.calculatePrice(Arrays.asList(request))).thenCallRealMethod();

        final CompletableFuture<List<Response>> response =controller.calculatePrice(Arrays.asList(request,request));
        Assert.assertEquals(response.get().size(),2);
    }
    @Test
    public void calculatePriceTest3()  {

        final CompletableFuture<List<Response>> response =controller.calculatePrice(Arrays.asList(request,request));
        CompletableFutureAssert<List<Response>> listAssert = Assertions.assertThat(response).isDone();
        listAssert.isExactlyInstanceOf(CompletableFuture.class);
    }






}
