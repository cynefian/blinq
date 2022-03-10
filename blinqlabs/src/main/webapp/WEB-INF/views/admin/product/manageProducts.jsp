<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<page:user-landing>
	<jsp:body>
		<div id="page-wrapper">
			<div class="features-tabs-section" style="margin-top: 100px;">
    			<div class="container">
					<div>
						<button type="button" class="btn btn-info" data-toggle="modal" data-target="#productCreateModal" data-whatever="@fat">Create new Product</button>
						<br/><br/>
					</div>
					<div class="modal fade" id="productCreateModal" tabindex="-1" role="dialog" aria-labelledby="productCreateModalLabel1">
			                <div class="modal-dialog" role="document">
			                    <div class="modal-content">
			                    <form class="form-horizontal m-t-20" action="${contextPath}/admin/adminProductList" method="POST" name="productForm" modelAttribute="productBean">
			                        <div class="modal-header">
			                            <h4 class="modal-title" id="productCreateModalLabel1">Create Product</h4>
			                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			                        </div>
			                        <div class="modal-body">
			                            
			                        	<div class="form-group">
			                                <div>
			                                    <label for="product-name" class="control-label">Product Name:</label>
			                                	<input class="form-control form-control-lg" type="text" required placeholder="Product Name" id="TX_PRODUCT_NAME" name="TX_PRODUCT_NAME" path="TX_PRODUCT_NAME" >
			                                </div>
			                                
			                                <div>
			                                	<label for="product-desc" class="control-label">Description</label>
			                                	<textarea class="form-control" rows="3" placeholder="Text Here..." id="TX_PRODUCT_DESC" name="TX_PRODUCT_DESC" path="TX_PRODUCT_DESC"></textarea>
			                                </div>
			                                
			                                <div>
			                                	<label for="fl-active" class="control-label">Active?</label>
			                                		<select class="custom-select mr-sm-2" id="FL_ACTIVE" name="FL_ACTIVE" path="FL_ACTIVE">
			                                            <option selected>Choose...</option>
			                                            <option value="true">YES</option>
			                                            <option value="false">NO</option>
			                                        </select>
			                                </div>
			                                
			                                <div>
			                                	<label for="fl-license" class="control-label">Is this product licensable?</label>
			                                		<select class="custom-select mr-sm-2" id="FL_LICENSE" name="FL_LICENSE" path="FL_LICENSE">
			                                            <option selected>Choose...</option>
			                                            <option value="true">YES</option>
			                                            <option value="false">NO</option>
			                                        </select>
			                                </div>
			                            </div>
			                                
			                            
			                        </div>
			                        <div class="modal-footer">
			                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			                             <button type="submit" class="btn btn-primary" >Create Product</button> 
			                           
			                        </div>
			                        </form>
			                    </div>
			                </div>
			            </div>    
    	
		
					<c:if test="${not empty productList}">
		 	 <table class="table table-hover">
				  <thead class="thead-default">
				    <tr>
				      <th>ID #</th>
				      <th>Product</th>
				      <th>Active Status</th>
				      <th>Licensable ?</th>
				      <th>Action</th>
				    </tr>
				  </thead>
				  <tbody>
				  
				  <c:forEach var="entry" items="${productList}">
				  
				  <tr>
				      <th scope="row">${entry.ID}</th>
				      <td>${entry.TX_PRODUCT_NAME}</td>
				      <td>
					      <c:if test="${entry.FL_ACTIVE==true}">
					      		Active
					      </c:if>
					      <c:if test="${entry.FL_ACTIVE!=true}">
					      		Inactive
					      </c:if>
				      </td>
				      <td>
				      	<c:if test="${entry.FL_LICENSE==true}">
					      		YES
					      </c:if>
					      <c:if test="${entry.FL_LICENSE!=true}">
					      		NO
					      </c:if>
				      </td>
				      <td>
				      	<button type="button" class="btn btn-info" data-toggle="modal" data-target="#productEditModal" 
				      	data-whatever="HELLO">Edit</button>
				      </td>
				    </tr>
				</c:forEach>
				  
				    
				  </tbody>
				</table>
		</c:if>
		
		<c:if test="${empty productList}">
			<span>You do not have any Products yet.</span>
		</c:if>
				</div>
			</div>
		</div>
		
		
		<div class="modal fade" id="productEditModal" tabindex="-1" role="dialog" aria-labelledby="productEditModalLabel1">
			                <div class="modal-dialog" role="document">
			                    <div class="modal-content">
			                    <form class="form-horizontal m-t-20" action="${contextPath}/admin/adminProductList" method="PUT" name="productForm" modelAttribute="productBean">
			                        <div class="modal-header">
			                            <h4 class="modal-title" id="productEditModalLabel1">Edit Product</h4>
			                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			                        </div>
			                        <div class="modal-body">
			                            
			                        	<div class="form-group">
			                                <div>
			                                    <label for="product-name" class="control-label">Product Name:</label>
			                                	<input class="form-control form-control-lg" type="text" required id="TX_PRODUCT_NAME" name="TX_PRODUCT_NAME" path="TX_PRODUCT_NAME" >
			                                </div>
			                                
			                                <div>
			                                	<label for="product-desc" class="control-label">Description</label>
			                                	<textarea class="form-control" rows="3" id="TX_PRODUCT_DESC" name="TX_PRODUCT_DESC" path="TX_PRODUCT_DESC"></textarea>
			                                </div>
			                                
			                                <div>
			                                	<label for="fl-active" class="control-label">Active?</label>
			                                		<select class="custom-select mr-sm-2" id="FL_ACTIVE" name="FL_ACTIVE" path="FL_ACTIVE">
				                                			<option selected>Choose...</option>
				                                            <option value="true">YES</option>
				                                            <option value="false">NO</option>
				                                	</select>
			                                </div>
			                                
			                                <div>
			                                	<label for="fl-license" class="control-label">Is this product licensable?</label>
			                                		<select class="custom-select mr-sm-2" id="FL_LICENSE" name="FL_LICENSE" path="FL_LICENSE">
			                                         		<option selected>Choose...</option>
				                                            <option value="true">YES</option>
				                                            <option value="false">NO</option>
				                                	
			                                        </select>
			                                </div>
			                            </div>
			                                
			                            
			                        </div>
			                        <div class="modal-footer">
			                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			                             <button type="submit" class="btn btn-primary" >Update Product</button> 
			                           
			                        </div>
			                        </form>
			                    </div>
			                </div>
			            </div>
			            
			            <script>
			            
			            $('#productEditModal').on('show.bs.modal', function(e) {

			            	 var button = $(event.relatedTarget); // Button that triggered the modal
			            	 var entry = button.data('whatever'); // Extract info from data-* attributes
			            	 
			            	 var modal = $(this);
			            	    modal.find('#TX_PRODUCT_NAME').val(entry);
			            	    
			            	    
			              
			            })
			            </script>
	</jsp:body>
</page:user-landing>