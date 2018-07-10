#!/usr/bin/python
# -*- coding: UTF-8 -*-

print 'this is a test of code path in try...except...else...finally'
print '************************************************************'

def exceptTest():
	try:
		print 'doing some work, and maybe exception will be raised'
		raise IndexError('index error')
#print 'after exception raise'
#return 0

	except KeyError, e:
		print 'in KeyError except'
		print e
		#return 1
	except IndexError, e:
		print 'in IndexError except'
		print e
		#return 2
	except ZeroDivisionError, e:
		print 'in ZeroDivisionError'
		print e
		#return 3
	else:
		print 'no exception'
		#return 4
	finally:
		print 'in finally'
		#return 5

resultCode = exceptTest()
print resultCode