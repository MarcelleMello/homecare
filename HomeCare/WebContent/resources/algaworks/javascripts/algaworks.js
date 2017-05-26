var AW = AW || {};

AW.onSidebarToggleRequest = function(event) {
  event.preventDefault();
  $(this).blur();

  $('.js-sidebar, .js-content').toggleClass('is-toggled');
};


AW.onMenuGroupClick = function(event) {
	//alert("ok");
  var subItems = $(this).parent().find('ul');

  if (subItems.length) {
    event.preventDefault();
    $(this).parent().toggleClass('is-expanded');
  }
};

AW.initMenu = function() {
  $('.js-menu > ul > li > a').bind('click', AW.onMenuGroupClick);
  $('.aw-menu__item .is-active').parents('.aw-menu__item').addClass('is-expanded is-active');
};

$(function() {
  if (AW.init) {
    AW.init();
  }

  AW.initMenu();
  //AW.initStickyTableHeaders();
  // Bind events
  //$('.js-sidebar-toggle').bind('click', AW.onSidebarToggleRequest);
 // $('.js-search-modal-trigger-show').bind('click', AW.onSearchModalShowRequest);
 // $('.js-search-modal-close').bind('click', AW.onSearchModalCloseRequest);
});