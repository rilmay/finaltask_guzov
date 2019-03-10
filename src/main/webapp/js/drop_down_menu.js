function openCloseMenu(id) {
    var doc = document.getElementById(id);
    var style = doc.style.display;
    if(style == 'none'){
        doc.style.display = 'block';
    }else {
        doc.style.display = 'none';
    }
}