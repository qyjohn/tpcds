#
# Location of the input files (*.dat) on S3
#
s3='s3://my-bucket/folder/'
#
# IAM role for the import
#
iam='arn:aws:iam::xxxxxxxxxxxx:role/role_name'
#
# AWS Region
#
region='ap-southeast-2';
#
# COPY call_center FROM 's3_url' iam_role 'iam_role' region 'ap-southeast-2'; 
#
for s in `find . -name '*.dat'`;
do
  a=${s#*/}
  b=${a%.*}
  echo COPY $b FROM \'$s3$b\' iam_role \'$iam\' region \'$region\'
done
