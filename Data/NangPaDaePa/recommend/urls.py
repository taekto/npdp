from django.urls import include, path
from rest_framework.urlpatterns import format_suffix_patterns
from recommend import views

urlpatterns = [
    path('users', views.users),
]

urlpatterns = format_suffix_patterns(urlpatterns)