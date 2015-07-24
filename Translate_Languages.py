from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time
import requests
from bs4 import BeautifulSoup
import tkinter.filedialog

url_name = 'https://translate.google.co.in'
driver = webdriver.Firefox()
driver.get(url_name)
file_read = '/home/aditya/input_file'
q = ''
f_open = open(file_read, 'r')
line = f_open.readline()
while line != '':
   q = q + line
   line = f_open.readline()
elem = driver.find_element_by_name("text")
elem.send_keys(q)
elem.send_keys(Keys.RETURN)
elem_button = driver.find_element_by_id("gt-tl-gms")
elem_button.click()
elem1 = driver.find_element_by_id(':3i') ## translates this to hindi, change language here by changing id
elem1.click()
elem_title = driver.find_element_by_id('result_box')
print(elem_title.text)
abs_path = '/home/aditya/output_file'
f = open(abs_path, 'w')
f.write(elem_title.text)
time.sleep(10)
f_open.close()
f.close()
driver.close()
