function toggleSorting(column) {
    var sortOrder = "name"
    var currentSortDirection = "asc";
    var newSortDirection = currentSortDirection === 'asc' ? 'desc' : 'asc';
    window.location.href = '/employees/page/1?sortBy=' + column + '&sortOrder=' + sortOrder + '&sortDirection=' + newSortDirection;
}