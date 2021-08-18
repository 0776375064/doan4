document.querySelectorAll('.sidebar-submenu').forEach(e => {
    e.querySelector('.sidebar-menu-dropdown').onclick = (event) => {
        event.preventDefault()
        e.querySelector('.sidebar-menu-dropdown .dropdown-icon').classList.toggle('active')

        let dropdown_content = e.querySelector('.sidebar-menu-dropdown-content')
        let dropdown_content_lis = dropdown_content.querySelectorAll('li')

        let active_height = dropdown_content_lis[0].clientHeight * dropdown_content_lis.length

        dropdown_content.classList.toggle('active')

        dropdown_content.style.height = dropdown_content.classList.contains('active') ? active_height + 'px' : '0';
    }
})


let overlay = document.querySelector('.overlay')
let sidebar = document.querySelector('.sidebar')

$('#mobile-toggle').on('click', function () {
    sidebar.classList.toggle('active')
    overlay.classList.toggle('active')
});

$('#sidebar-close').on('click', function () {
    sidebar.classList.toggle('active')
    overlay.classList.toggle('active')
});

// close modal
$(document).on('click', '.modal-close', function (e) {
    $(".modal").removeClass("open");
    $(".modal-body").html("");
});

// close modal
$(document).on('click', '.modal-duy-close', function (e) {
    $(".modal-duy").removeClass("open");
});