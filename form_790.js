//@ sourceURL=com.sms.plugin.formscript.form_790
$.u.define('com.sms.plugin.formscript.form_790', 'com.sms.plugin.formscript.baseprop', {
  init: function (options) {
    this._options = options;
    debugger;
  },
  bind_event: function (formSel, mode)  {
	   this.i18n = com.sms.plugin.formscript.form_790.i18n;
	    //供应商资质报备业务逻辑begin
	    this.is_have_supplier = this.getComp(formSel, "is_have_supplier");
	    //已有供应商名称
	    this.supplierName = this.getComp(formSel, "supplierName");
	    //手工单供应商名称
	    this.supplier_name = this.getComp(formSel, "supplier_name");
	    this.supplierName.method("hide");
	    this.supplier_name.method("setRequired",true);

	    //是否已有供应商
	    this.is_have_supplier.method("click", this.proxy(function (checked) {
	      if (checked) {
	    	  this.supplierName.method("show");
	    	  this.supplier_name.method("hide");
	      } else {
	    	  this.supplierName.method("hide");
	    	  this.supplier_name.method("show");
	      }
	    }));
	    this.supplier_name.method("click", this.proxy(function (checked) {
		      if (checked) {
		    	  this.supplierName.method("show");
		    	  this.supplier_name.method("hide");
		      } else {
		    	  this.supplierName.method("hide");
		    	  this.supplier_name.method("show");
		      }
		    }));   
	    var newName = this.supplier_name.method("getInputBox");
 },
 add_render: function (formSel) {
	  this.bind_event(formSel,"ADD");
	    
 },
 edit_render: function (formSel) {
	  this.bind_event(formSel,"EDIT");
 },
 before_add_save: function () {
	 var newName = this.supplier_name.method("getInputBox");
     var checkSupplier_name = newName["0"].value;
     $.u.ajax({
         url: $.u.config.contextpath + "/ctripoa/qunar/qunarSupplierAptitude/findQunarContract/" + checkSupplier_name,
         headers: { "Content-Type": "application/json" },
         type: "get",
         success : function(data){
        	alert(data);
        }
       });
     return false;
 },
  destroy: function () {
    this._super();
  }
}, { usehtm: false, usei18n: true });
