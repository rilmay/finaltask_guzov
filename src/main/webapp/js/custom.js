function ondeleteclick(formid) {
    swal({
        title: 'Are you sure?',
        dangerMode: true,
        buttons: {
            cancel: true,
            confirm: {
                value: "delete"
            }
        }
    }).then((value) => {
        if(value == "delete"){
        document.getElementById(formid).submit();
        swal(
            'Deleted!',
            'Your file has been deleted.',
            'success'
        );
    }
})
}