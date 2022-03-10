<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

	<!-- start pricing table -->
        <section>
            <div class="container lg-container">

                <div class="section-title text-center margin-60px-bottom md-margin-50px-bottom xs-margin-30px-bottom">
                    <h3 class="font-weight-700 margin-10px-bottom">Simple Pricing for Blinqlabs Saas products</h3>
                </div>

            </div>

            <div class="container">

                <div class="row price price-style1">

                    <!-- start table -->
                    <div class="col-lg-4 sm-margin-35px-bottom wow fadeInUp" data-wow-delay=".2s">
                        <div class="item text-center">
                            <div class="type">
                                <h4 class="font-size22 sm-font-size20 xs-font-size18">Basic</h4>
                            </div>
                            <div class="value">
                                <h3>99<span>$</span></h3>
                                <span class="per"> / Per Month</span>
                            </div>
                            <div class="features">
                                <ul>
                                	<li>1 Tool of your choice</li>
                                    <li>8x5 Tech Support</li>
                                    <li>Shared Tenants</li>
                                    <li>10 GB Storage</li>
                                    <li>2 GB RAM</li>
                                </ul>
                            </div>
                            <div class="order">
                                <a href="javascript:void(0);" class="butn style-two reverse">Purchase Now</a>
                            </div>
                        </div>
                    </div>
                    <!-- end table -->

                    <!-- start table -->
                    <div class="col-lg-4 sm-margin-35px-bottom wow fadeInUp" data-wow-delay=".4s">
                        <div class="item text-center active bg-theme-90">
                            <div class="type">

                                <h4 class="font-size22 sm-font-size20 xs-font-size18">Standard</h4>
                            </div>
                            <div class="value">
                                <h3>699*<span>$</span></h3>
                                <span class="per"> / Per Month</span>
                            </div>
                            <div class="features">
                                <ul>
                                    <li>Everything in Basic</li>
                                    <li>Choice of upto 3 tools</li>
                                    <li>30 GB Storage per Tool</li>
                                    <li>2 GB RAM per tool</li>
                                    <li>Hardward customization possible*</li>
                               
                                </ul>
                            </div>
                            <div class="order">
                                <a href="javascript:void(0);" class="butn style-two">Purchase Now</a>
                            </div>
                        </div>
                    </div>
                    <!-- end table -->

                    <!-- start table -->
                    <div class="col-lg-4 wow fadeInUp" data-wow-delay=".6s">
                        <div class="item text-center">
                            <div class="type">
                                <h4 class="font-size22 sm-font-size20 xs-font-size18">Advanced</h4>
                            </div>
                            <div class="value">
                                <h3>?<span>$</span></h3>
                                <span class="per"> / Per Month</span>
                            </div>
                            <div class="features">
                                <ul>
                               	     <li>any number of products</li>
                                    <li>24/7 Tech Support</li>
                                    <li>Dedicated Tenants</li>
                                    <li>Custom Hardware configuration</li>
                                </ul>
                            </div>
                            <div class="order">
                                <a href="javascript:void(0);" class="butn style-two reverse">Request Quote</a>
                            </div>
                        </div>
                    </div>
                    <!-- end table -->
               </div>
            </div>
        </section>
  
