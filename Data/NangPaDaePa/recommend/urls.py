from django.conf.urls import url
from rest_framework.urlpatterns import format_suffix_patterns  
from recommend import views

urlpatterns = [
    url(r'^$', views.test),
]

urlpatterns = format_suffix_patterns(urlpatterns)