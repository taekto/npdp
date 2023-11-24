# Generated by Django 4.2.4 on 2023-08-15 07:53

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('recommend', '0003_recipe'),
    ]

    operations = [
        migrations.CreateModel(
            name='Member',
            fields=[
                ('member_id', models.AutoField(primary_key=True, serialize=False)),
                ('email', models.CharField(max_length=255)),
            ],
            options={
                'db_table': 'member',
                'managed': False,
            },
        ),
    ]